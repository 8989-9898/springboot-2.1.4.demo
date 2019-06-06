package com.ly.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.annotation
 * @ClassName: DateTimeValidater
 * @Author: lin
 * @Description: 定义日期格式认证的具体实现类
 * @Date: 2019-06-06 16:55
 * @Version: 1.0
 */
public class DateTimeValidater implements ConstraintValidator<DateTime, String> {

   private DateTime dateTime;

   @Override
   public void initialize(DateTime constraint) {
      this.dateTime=constraint;
   }

   @Override
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      // 是否为空
      if (obj==null) {
         return false;
      }
      // 长度是否一致
      String format=dateTime.format();
      if (obj.length()!=format.length()) {
         return false;
      }
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
      try {
         // 是否可以转换
         simpleDateFormat.parse(obj);
      }catch (ParseException e){
         return false;
      }
      return true;
   }
}
