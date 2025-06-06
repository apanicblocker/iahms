package cn.apkr.framework.aspectj;



/**
 * 多数据源处理
 */
//@Aspect
//@Order(1)
//@Component
//public class DataSourceAspect {
//
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Pointcut("@annotation(cn.apkr.common.annotation.DataSource)"
//            + "|| @within(cn.apkr.common.annotation.DataSource)")
//    public void dsPointCut() {
//
//    }
//
//    @Around("dsPointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        DataSource dataSource = getDataSource(point);
//
//        if (Objects.nonNull(dataSource)) {
//            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
//        }
//        try {
//            return point.proceed();
//        } finally {
//            // 销毁数据源 在执行方法之后
//            DynamicDataSourceContextHolder.clearDataSourceType();
//        }
//    }
//
//    /**
//     * 获取需要切换的数据源
//     */
//    public DataSource getDataSource(ProceedingJoinPoint point) {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
//        if (Objects.nonNull(dataSource)) {
//            return dataSource;
//        }
//        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
//    }
//}
