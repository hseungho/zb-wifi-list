package service.repository.base.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Service 의 실행 메소드 단위로 트랜잭션을 구현하고 싶어서<br>
 * Spring 의 @Transactional 을 구현하기 위해 만들었지만,<br>
 * 테스트 결과, 실제로 한 메소드 안에서 한 커넥션을 가지고 트랜잭션이 구현되진 않음.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface Transactional {
}

