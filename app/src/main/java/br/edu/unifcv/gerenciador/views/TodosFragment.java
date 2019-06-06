package br.edu.unifcv.gerenciador.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.unifcv.gerenciador.R;
import br.edu.unifcv.gerenciador.adapter.ConvidadoAdapter;
import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.listener.OnConvidadoListener;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.service.ConvidadoService;

public class TodosFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private ConvidadoService mConvidadoService;
    private OnConvidadoListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todos, container, false);

        Context context = view.getContext();

        this.mViewHolder.mRecyclerViewTodos = view.findViewById(R.id.recycler_convidados);
        this.mViewHolder.mTextPresente = view.findViewById(R.id.text_presente);
        this.mViewHolder.mTextAusente = view.findViewById(R.id.text_ausente);
        this.mViewHolder.mTextTodos = view.findViewById(R.id.text_todos);


        this.mConvidadoService = new ConvidadoService(context);

        listener = new OnConvidadoListener() {
            @Override
            public void onClickList(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(ConvidadoConstants.BundleConstants.CONVIDADO_ID, id);
                Intent intent = new Intent(getContext(), ConvidadoFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {

            }
        };

        // definir um layout
        this.mViewHolder.mRecyclerViewTodos.setLayoutManager(
                new LinearLayoutManager(context));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadDashBoard();
        this.loadConvidados();
    }

    private void loadConvidados() {
        // todos as pessoas do banco
        List<Convidado> convidados = this.mConvidadoService.findAll();

        // definir um adapter
        ConvidadoAdapter adapter = new ConvidadoAdapter(convidados, listener);
        this.mViewHolder.mRecyclerViewTodos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadDashBoard() {
        ConvidadosCount convidadosCount = mConvidadoService.count();

        this.mViewHolder.mTextPresente.setText(String.valueOf(convidadosCount.getPresente()));
        this.mViewHolder.mTextAusente.setText(String.valueOf(convidadosCount.getAusente()));
        this.mViewHolder.mTextTodos.setText(String.valueOf(convidadosCount.getTodos()));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewTodos;
        TextView mTextPresente;
        TextView mTextAusente;
        TextView mTextTodos;
    }

}
