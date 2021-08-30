package edu.kpi.developmentmethods.auth;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {
    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            String token = (String) authenticationRequest.getSecret();
            // TODO: вам нужно реализовать проверку того, что полученый токен (переменная token)
            // соответствует пользователю в вашей системе и вернуть даные (UserDetails) об этом пользователе
            if (true) {
                // конструктор UserDetails принимает на вход 2 параметра: первый — это идентификатор пользователя
                // в вашей системе (в нашем приложении — это email), а второй — список ролей пользователя.
                // В нашем приложении оставляем этот список пустым.
                emitter.onNext(new UserDetails("example@test.com", new ArrayList<>()));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
