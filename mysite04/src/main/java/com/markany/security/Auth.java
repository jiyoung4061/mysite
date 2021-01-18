package com.markany.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // 이 annotation이 어디로 갈것인가!(method면 method로 가서 붙는다!)
@Retention(RetentionPolicy.RUNTIME) // 종속기간-anntation의 유지기간!
						// source : source에서만 유지하고 compile시 없애버리겟다
						// runtime : 실행하는 동안에도 계속 유지함.(메모리에도 같이 올림)
						// class : 메모리에 올릴때 annotation을 뺀다.
public @interface Auth {
	public String value() default "USER"; // @auth(value="") 설정
	public Role role() default Role.USER;
}
