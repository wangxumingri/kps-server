

## 日志

```
logging:
  level:
    root: info
    # 只打印sql语句
    com.wss.kps.dao: debug
  file:
    name: D:/log/kps.log
```

**使用示例**

```
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    ...
    
    logger.error("处理参数校验异常:{}",e.getMessage(),e);
```