package enterprise.minura.cyb3rodds.settings.changePassFrag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import enterprise.minura.cyb3rodds.MainActivity;
import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.LoginActivity;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangePassFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangePassFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePassFrag extends Fragment implements RestObserver{
    String err="";

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        final User gb= (User) getActivity().getApplicationContext();


        try {
            err = response.getJSONObj().getString("errcode");
        } catch (JSONException e) {

        }

        Toast.makeText(getActivity(), err , Toast.LENGTH_SHORT).show();
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Skyrunner mSky;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChangePassFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePassFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePassFrag newInstance(String param1, String param2) {
        ChangePassFrag fragment = new ChangePassFrag();
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
        View v= lf.inflate(R.layout.fragment_change_pass, container, false);
        final User gb= (User) getActivity().getApplicationContext();

        final EditText old= v.findViewById(R.id.oldpass);
        final EditText newp= v.findViewById(R.id.newpass);
        final EditText newc= v.findViewById(R.id.newpassconfirm);
        Button change= v.findViewById(R.id.change);

        mSky= new Skyrunner(20);
        mSky.addObserver(this);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String old1= old.getText().toString();
                String new1= newp.getText().toString();
                String new2= newc.getText().toString();

                if(old1.equals("")|| old1.equals(" ")){
                    Toast.makeText(getActivity(),"Please insert a valid password!", Toast.LENGTH_SHORT).show();
                }
                else if(new1.equals("") || new1.equals(" ")){
                    Toast.makeText(getActivity(),"Please insert a new valid password!", Toast.LENGTH_SHORT).show();
                }
                else if(new2.equals("") || new2.equals(" ")){
                    Toast.makeText(getActivity(),"Please confirm your new valid password!", Toast.LENGTH_SHORT).show();
                }
                else if(!new1.equals(new2)){
                    Toast.makeText(getActivity(),"Your passwords doesn't match!", Toast.LENGTH_SHORT).show();
                }

                else{

                    RequestR2D2 req= new RequestR2D2(Config.WSCHANGEPASS,null,RequestR2D2.GET);
                    req.addParValue("id",gb.getId());
                    req.addParValue("password",old1);
                    req.addParValue("nova",new1);

                    mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);



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
