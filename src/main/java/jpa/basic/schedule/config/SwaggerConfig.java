package jpa.basic.schedule.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("일정 관리 프로젝트 API")
                        .description("일정 등록, 조회, 수정, 삭제 기능을 제공하는 API 명세서입니다.")
                        .version("1.0.0"));
    }
}