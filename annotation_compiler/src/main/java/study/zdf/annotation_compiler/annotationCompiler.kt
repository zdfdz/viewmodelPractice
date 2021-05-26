package study.zdf.annotation_compiler

import com.google.auto.service.AutoService
import study.zdf.annotation.BindPath
import java.io.Writer
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * @author ZhengDeFeng
 * @description: 生成Java代码
 * @date :2021/5/24 20:40
 */
@AutoService(Processor::class) // 注册注解处理器
class annotationCompiler : AbstractProcessor() {
    var filer: Filer? = null
    var message: Messager? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        filer = processingEnv?.filer
        message = processingEnv?.messager
        message?.printMessage(Diagnostic.Kind.WARNING, "annotationCompiler init ...")
    }

    // 声明当前注解处理器需要处理哪些注解
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val annotationSet = hashSetOf<String>()
        annotationSet.add(BindPath::class.java.canonicalName)
        message?.printMessage(Diagnostic.Kind.WARNING, "getSupportedAnnotationTypes init ...")
        return annotationSet
    }

    // 设置支持的java版本
    override fun getSupportedSourceVersion(): SourceVersion {
        message?.printMessage(Diagnostic.Kind.WARNING, "getSupportedSourceVersion init ...")
        return processingEnv.sourceVersion
    }


    // 能去当前模块中搜索被注解的类
    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        message?.printMessage(Diagnostic.Kind.WARNING, "process init ...")
        // 搜索当前模块用到bindPath注解的类
        val elementsAnnotatedWith = roundEnv?.getElementsAnnotatedWith(BindPath::class.java)
        // 遍历所有的类,然后把这个类名和注解里面携带的key拿出来
        val map = HashMap<String, String>()
        elementsAnnotatedWith?.forEach {
            if (it is TypeElement) {
                val value = it.qualifiedName.toString()
                val key = it.getAnnotation(BindPath::class.java).path
                map[key] = "$value.class"
            }
        }

        // 生成文件
        if (map.size > 0) {
            var writer: Writer? = null
            try {
                var utilName = "ActivityUtils" + System.currentTimeMillis()
                // 生成 java 代码
                var sourceFile = filer?.createSourceFile("study.zdf.$utilName")
                writer = sourceFile?.openWriter()
                val sb = StringBuffer()
                sb.append("package study.zdf.util;\n")
                sb.append("import study.zdf.arouter.Arouter;\n")
                sb.append("import study.zdf.arouter.IRouter;\n")
                sb.append("class $utilName: IRouter {\n")
                sb.append("override fun putActivity() {\n")
                val iterator = map.keys.iterator()
                while (iterator.hasNext()){
                    val key = iterator.next()
                    val value = map[key]
                    sb.append("Arouter.instance.addActivity(\"$key\",$value)")
                }
                sb.append("}\n}")
                writer?.write(sb.toString())
            } catch (e: Exception) {

            }finally {
                writer?.close()
            }
        }
        return false
    }

}
