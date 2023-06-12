package ueg.tc.fluencee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(
		scanBasePackages = {
				"ueg.tc.fluencee.*",
				//Para funcionamento da Arquitetura
				"br.ueg.prog.webi.*"}
)
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class },
		basePackages = {
				"ueg.tc.fluencee.*",
				//Para funcionamento da Arquitetura
				"br.ueg.prog.webi.api.*"}
)
public class FluenceeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluenceeApplication.class, args);
	}

}
