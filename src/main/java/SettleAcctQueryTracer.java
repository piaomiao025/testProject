///* BTrace Script Template */
//import com.sun.btrace.annotations.*;
//import static com.sun.btrace.BTraceUtils.*;
//
//
//@BTrace
//public class SettleAcctQueryTracer {
//    @OnMethod(clazz="com.egfbank.settleee.adapter.AcctInfoAdapter", method="queryAcctInfo", location=@Location(Kind.RETURN))
//    public static void printMethodRunTime(@Self com.egfbank.settleee.adapter.AcctInfoAdapter self, @ProbeClassName String probeClassName, @
//            Duration long duration, String acctNo, @Return com.egfbank.settleee.vo.AccountInfoResponseVo vo){
//        println(probeClassName + ", duration: " + duration / 1000000 + " ms  11");
//        println("入参：" + acctNo);
//        println("出参：" + vo);
//        println("   ");
//    }
//}