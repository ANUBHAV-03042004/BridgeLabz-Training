package createrolebasedaccesscontrol;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RoleAllowed {
String Role() default "User";
}
