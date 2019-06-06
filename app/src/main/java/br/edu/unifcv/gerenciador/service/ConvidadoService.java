package br.edu.unifcv.gerenciador.service;

import android.content.Context;

import java.util.List;

import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.repository.ConvidadoRepository;

public class ConvidadoService {

    private ConvidadoRepository convidadoRepository;

    public ConvidadoService(Context context) {
        this.convidadoRepository = ConvidadoRepository.getInstance(context);
    }

    public boolean insert(Convidado convidado) {
        return this.convidadoRepository.save(convidado);
    }

    public List<Convidado> findAll() {
        return this.convidadoRepository.getConvidadoByQuery("select * from " +
                DataBaseConstants.CONVIDADO.TABLE_NAME);

    }

    public ConvidadosCount count() {
        return this.convidadoRepository.getCount();
    }
}
