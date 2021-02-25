package com.lgm.ASM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author:李罡毛
 * @date:2021/2/25 16:14
 */
public class ClassWriteTest {
    public static void main(String[] args) {
        org.objectweb.asm.ClassWriter cw = new org.objectweb.asm.ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, 1).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("pkg.Comparable", b);
        System.out.println(c.getMethods()[0].getName());


    }
}
