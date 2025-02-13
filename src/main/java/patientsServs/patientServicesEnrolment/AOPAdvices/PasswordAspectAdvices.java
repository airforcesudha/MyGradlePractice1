package patientsServs.patientServicesEnrolment.AOPAdvices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class PasswordAspectAdvices {
	
	private static final String STATIC_PASSWORD = "Reddy@085";
	
	
	@Before("execution(* patientsServs.patientServicesEnrolment.controllers.PatientServicesController.*Delete*(..))")
    public void logBeforeDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String password = (String)args[1];
        if(!password.equalsIgnoreCase(STATIC_PASSWORD)) {
        	throw new SecurityException("INCORRECT PASSWORD : GIVEN PASSWORD WAS WRONG");
        }
    }
	
	
    
    
}
