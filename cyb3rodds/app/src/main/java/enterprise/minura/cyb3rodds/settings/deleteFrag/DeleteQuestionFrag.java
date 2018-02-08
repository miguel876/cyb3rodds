package enterprise.minura.cyb3rodds.settings.deleteFrag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.LoginActivity;
import enterprise.minura.cyb3rodds.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeleteQuestionFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeleteQuestionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteQuestionFrag extends Fragment implements RestObserver{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Skyrunner mSky;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DeleteQuestionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteQuestionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteQuestionFrag newInstance(String param1, String param2) {
        DeleteQuestionFrag fragment = new DeleteQuestionFrag();
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
        View v= lf.inflate(R.layout.fragment_delete_question, container, false);

        final User gb= (User) getActivity().getApplicationContext();

        final TextView question= v.findViewById(R.id.question);
        final TextView answer= v.findViewById(R.id.answer);
        Button delete= v.findViewById(R.id.delete);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);

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

                    RequestR2D2 req= new RequestR2D2(Config.WSDELETEUSER,null,RequestR2D2.GET);
                    req.addParValue("id",gb.getId());


                    mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);
                    Intent it= new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
                    Toast.makeText(getActivity(),"Your account has benn sucessfully Deleted! Feel free to register a new one!", Toast.LENGTH_LONG).show();
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

    @Override
    public void receivedResponse(ResponseR2D2 response) {

    }

    @Override
    public void startConnecting() {

    }

    @Override
    public void endConnecting() {

    }

    @Override
    public void requestTimeout() {

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
