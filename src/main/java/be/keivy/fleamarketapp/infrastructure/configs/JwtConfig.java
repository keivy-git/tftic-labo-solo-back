package be.keivy.fleamarketapp.infrastructure.configs;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * <h1>Configuration JWT</h1>
 * <p>
 * Cette classe est responsable de la configuration JWT.
 * Elle est annotée avec @Component pour indiquer qu'une instance de cette classe doit être créée au démarrage.
 * Elle est également annotée avec @ConfigurationProperties(prefix = "jwt") pour lier les propriétés préfixées par "jwt"
 * des fichiers de configuration de l'application aux champs de cette classe.
 * </p>
 * <p>
 * La classe a trois champs : algorithm, secret et expireAt, qui représentent respectivement l'algorithme JWT, la clé secrète,
 * et le temps d'expiration. Ces champs sont remplis avec des valeurs provenant des fichiers de configuration de l'application.
 * </p>
 * <p>
 * La classe a également un champ SecretKey, secretKey, qui est utilisé pour stocker la clé secrète dans un format adapté
 * à la création d'une signature JWT. Ce champ est rempli dans la méthode init, qui est annotée avec @PostConstruct
 * pour s'assurer qu'elle est exécutée après que Spring ait terminé l'injection des champs.
 * </p>
 */

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter @Setter
public class JwtConfig {

    private String algorithm;
    private String secret;
    private long expireAt;

    private SecretKey secretKey;

    /**
     * Cette méthode est exécutée après que Spring ait terminé l'injection des champs.
     * Elle crée un nouvel objet SecretKeySpec en utilisant les champs secret et algorithm et l'assigne au champ secretKey.
     */
    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm);
    }
}
