package com.lgm.ASM;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.springframework.asm.Opcodes.ASM4;
import static org.springframework.asm.Opcodes.INVOKESTATIC;

/**
 * @author:李罡毛
 * @date:2021/2/25 18:21
 */
public class ClassTransformTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassReader cr = new ClassReader(ClassPrinter.class.getClassLoader().getResourceAsStream("com/lgm/ASM/Tank.class"));
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM4,cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                return new MethodVisitor(ASM4,methodVisitor) {
                    @Override
                    public void visitCode() {
                        visitMethodInsn(INVOKESTATIC,"com/lgm/ASM/TimeProxy",
                                "before","()V",false);
                    }
                };
            }
        };
        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray();

        MyClassLoader cl = new MyClassLoader();
        //Class c = cl.loadClass("com.lgm.ASM.Tank");
        cl.loadClass("com.lgm.ASM.TimeProxy");
        Class c2 = cl.defineClass("com.lgm.ASM.Tank", b2);

        c2.getConstructor().newInstance();//生成的新类，构造方法中也调用了before()方法，所以创建实例就会调用一次切面


        String path = (String)System.getProperties().get("user.dir");
        File f = new File(path + "/com/lgm/ASM/");
        f.mkdirs();

        FileOutputStream fos = new FileOutputStream(new File(path + "/com/lgm/ASM/Tank_0.class"));
        fos.write(b2);
        fos.flush();
        fos.close();

    }
}
