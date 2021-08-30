package edu.kpi.developmentmethods.views;

import edu.kpi.developmentmethods.dto.Example;
import edu.kpi.developmentmethods.logic.Logic;
import edu.kpi.developmentmethods.storage.Storage;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import java.net.URI;

/**
 * Пример публично-доступного контроллера.
 * Методы этого контроллера доступны всем пользователям, в том числе и неаутентифицированным в системе.
 *
 * Такое поведение (доступность контроллера для всех пользователей) достигается при помощи аннотации
 * @Secured(SecurityRule.IS_ANONYMOUS)
 */
@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class PublicController {

    private final Logic logic;

    @Inject
    public PublicController(Logic logic) {
        this.logic = logic;
    }

    /**
     * Пример простейшего эндпоинта
     */
    @Get(
        value="/hello",
        produces = MediaType.TEXT_PLAIN
    )
    public String hello() {
        return "Hello world";
    }


    @Get(
            value = "/examples/{key}",
            produces = MediaType.APPLICATION_JSON
    )
    public HttpResponse<Example> getExample(String key) {
        var example = logic.getExample(key);
        if (example == null) {
            // Если по ключу нет сохранённого значения,
            // возвращаем 404 ошибку
            return HttpResponse.notFound();
        }
        // Возвращаем успешный ответ с кодом 200 и полученным значением
        return HttpResponse.ok(example);
    }

    /**
     * Пример эндпоинта, который принимает запросы HTTP PUT, которые содержат данные в теле запроса
     */
    @Put(
        value = "/examples/{key}",
        processes = MediaType.APPLICATION_JSON
    )
    public HttpResponse putExample(String key, @Body Example value) {
        boolean success = logic.putExampleIfNotExists(key, value);
        if (success) {
            // Возвращаем 201 Created, с ссылкой на созданный ресурс
            return HttpResponse.created(URI.create(String.format("/examples/%s", key)));
        } else {
            // В случае, если не удалось сохранить нужный объект из-за того,
            // что с этим ключом уже есть сохраненное значение,
            // возвращаем пользователю 409 ошибку
            return HttpResponse.status(HttpStatus.CONFLICT);
        }
    }

    @Delete(
            value = "/examples/{key}",
            produces = MediaType.APPLICATION_JSON
    )
    public HttpResponse deleteExample(String key) {
        logic.deleteExample(key);
        return HttpResponse.noContent();
    }

}
