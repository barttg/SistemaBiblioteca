package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.services.CopiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CopiaController {

    private final CopiaService copService;

}
