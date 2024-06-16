package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.dto.AuthenticateDTO;
import med.voll.api.infra.security.TokenJWT;
import med.voll.api.infra.security.TokenService;
import med.voll.api.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authnticateLogin(@RequestBody @Valid AuthenticateDTO authenticate) {

        var token = new UsernamePasswordAuthenticationToken(authenticate.user(), authenticate.password());

        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));

    }
}
