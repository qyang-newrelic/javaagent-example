package com.qyang.javaagent;



import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMain {
	public static void premain(String agentOps, Instrumentation inst) {
		instrument(agentOps, inst);
	}

	public static void agentmain(String agentOps, Instrumentation inst) {
		instrument(agentOps, inst);
	}

	private static void instrument(String agentOps, Instrumentation inst) {
		System.out.println(agentOps);
  }
/*
	private static void instrumentOld(String agentOps, Instrumentation inst) {
		System.out.println(agentOps);
		inst.addTransformer(new ClassFileTransformer() {
			public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
				return transformClass(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
			}
		});
		System.out.println(inst);
	}

	private static byte[] transformClass(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
		System.out.println("Transform loader " + loader + " className: " + className + " classBeingRedefined " + classBeingRedefined);
		ClassReader classReader = new ClassReader(classfileBuffer);
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classReader.accept(new MyClassClassVisitor(classWriter), 0);
		byte[] bytes = classWriter.toByteArray();
		return bytes;
	}
	static class MyClassClassVisitor extends ClassVisitor {
		public MyClassClassVisitor(ClassVisitor cv) {
			super(Opcodes.ASM5, cv);
		}

		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[]
				exceptions) {
			System.out.println("visit " + name + " desc " + desc);
			if ("say".equals(name)) {
				// do call
				MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
				methodVisitor.visitCode();
				methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
				methodVisitor.visitLdcInsn("CALL " + name);
				methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

				methodVisitor.visitEnd();
				return methodVisitor;
			}

			return super.visitMethod(access, name, desc, signature, exceptions);

		}
	}
  */
}

