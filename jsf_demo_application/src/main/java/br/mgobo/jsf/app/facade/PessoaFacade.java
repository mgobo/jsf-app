package br.mgobo.jsf.app.facade;

import br.mgobo.jsf.app.controllers.PessoaController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;

public class PessoaFacade extends PessoaController {
    public String actionValidationUpdateForm(Object data){
        StringBuilder sb = new StringBuilder("");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(data);
        violations.stream().forEach(s->sb.append(s.getMessage()).append("\n"));

        return sb.toString();
    }

    public String converterIdade(String idade) {
        if (idade != null && !idade.equals("")) {
            Integer ano = Integer.parseInt(idade.split("-")[0]);
            Integer anoCorrente = Instant.now().atZone(ZoneId.systemDefault()).getYear();
            Integer resultado = anoCorrente - ano;
            idade = (resultado < 1 ? resultado + " mes(es)" : (resultado == 1 ? resultado + " ano" : resultado + " anos"));
        }
        return idade;
    }
}
