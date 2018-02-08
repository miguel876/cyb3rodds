package enterprise.minura.cyb3rodds.settings.changePassFrag;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangePassQuestionFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangePassQuestionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePassQuestionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChangePassQuestionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePassQuestionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePassQuestionFrag newInstance(String param1, String param2) {
        ChangePassQuestionFrag fragment = new ChangePassQuestionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf= getActivity().getLayoutInflater();
        View v= lf.inflate(R.layout.fragment_change_pass_question, container, false);

        final User gb= (User) getActivity().getApplicationContext();

        final TextView question= v.findViewById(R.id.question);
        final TextView answer= v.findViewById(R.id.answer);
        Button delete= v.findViewById(R.id.delete);

        String quest= gb.getQuestion().toString();
        question.setText(quest);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans= gb.getAnswer().toString();
                String ans1= answer.getText().toString();

                if(ans1.equals("")|| ans1.equals(" ")){
                    Toast.makeText(getActivity(),"Please Insert an Answer!", Toast.LENGTH_SHORT).show();
                }
                else if(!ans1.equals(ans)){
                    Toast.makeText(getActivity(),"Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Fragment newFrag= new ChangePassFrag();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();

                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();
                }

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
