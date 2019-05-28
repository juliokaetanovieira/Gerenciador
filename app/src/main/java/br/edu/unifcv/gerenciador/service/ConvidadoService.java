package br.edu.unifcv.gerenciador.service;

import android.content.Context;

import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.repository.ConvidadoRepository;

public class ConvidadoService {

    private ConvidadoRepository convidadoRepository;

    public ConvidadoService(Context context) {
        this.convidadoRepository = ConvidadoRepository.getInstance(context);
    }

    public boolean insert(Convidado convidado) {
        return this.convidadoRepository.save(convidado);
    }
}
