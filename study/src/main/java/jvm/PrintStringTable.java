//package jvm;
//
//import sun.jvm.hotspot.memory.StringTable;
//import sun.jvm.hotspot.memory.SystemDictionary;
//import sun.jvm.hotspot.oops.Instance;
//import sun.jvm.hotspot.oops.InstanceKlass;
//import sun.jvm.hotspot.oops.OopField;
//import sun.jvm.hotspot.oops.TypeArray;
//import sun.jvm.hotspot.runtime.VM;
//import sun.jvm.hotspot.tools.Tool;
//
///**
// * Created by zz on 2022/5/12.
// */
//public class PrintStringTable extends Tool {
//    public PrintStringTable() {
//    }
//
//    public static void main(String args[]) throws Exception {
//        if (args.length == 0 || args.length > 1) {
//            System.err.println("Usage: java PrintStringTable <PID of the JVM whose string table you want to print>");
//            System.exit(1);
//        }
//        PrintStringTable pst = new PrintStringTable();
//        pst.execute(args);
//        pst.stop();
//    }
//
//    @Override
//    public void run() {
//        StringTable table = VM.getVM().getStringTable();
//        table.stringsDo(new StringPrinter());
//    }
//
//    class StringPrinter implements StringTable.StringVisitor {
//        private final OopField stringValueField;
//
//        public StringPrinter() {
//            InstanceKlass strKlass = SystemDictionary.getStringKlass();
//            stringValueField = (OopField) strKlass.findField("value", "[C");
//        }
//
//        @Override
//        public void visit(Instance instance) {
//            TypeArray charArray = ((TypeArray) stringValueField.getValue(instance));
//            StringBuilder sb = new StringBuilder();
//            for (long i = 0; i < charArray.getLength(); i++) {
//                sb.append(charArray.getCharAt(i));
//            }
//            System.out.println("Address: " + instance.getHandle() + " Content: " + sb.toString());
//        }
//    }
//
//}
