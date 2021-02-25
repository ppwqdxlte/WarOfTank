package com.lgm.ASM;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author:李罡毛
 * @date:2021/2/25 12:40
 */
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(ASM6);
    }

    public static void main(String[] args) {
        ClassVisitor cp = new ClassPrinter();
        try {
            /*1、asm处理字节码文件，其它格式找不到！比如.java就不能读取！
            * 2、JDK环境也要匹配！asm9.1还不支持JDK15！我试了下支持JDK11
            * 3、项目环境换成JDK11之后，运行报错：LinkageError!!!com/lgm/ASM/ClassPrinter has been compiled
            * by a more recent version of the Java Runtime (class file version 59.0),
            * this version of the Java Runtime only recognizes class file versions up to 55.0
            *
            * 也就是说以前用JDK15编译出来的.class字节码文件都不支持！故而要重新build一下项目才可以！换成11版本的字节码文件！*/
            ClassReader cr =new ClassReader(
                    ClassPrinter.class.getClassLoader().getResourceAsStream("com/lgm/ASM/Test1.class"));
            cr.accept(cp,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name+" extends "+superName+"{");
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println(name);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println(name+"()");
        return null;
    }
    @Override
    public void visitEnd() {

        System.out.println("}");
    }

}
