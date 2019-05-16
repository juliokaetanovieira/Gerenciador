package br.edu.unifcv.gerenciador.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.unifcv.gerenciador.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PresentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PresentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present, container, false);
    }
}
