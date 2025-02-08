package edu.ucan.stock.dto.records;

import edu.ucan.stock.enums.TipoUsuario;

public record RegisterRecord(String login, String senha, TipoUsuario tipoUsuario) {

}
