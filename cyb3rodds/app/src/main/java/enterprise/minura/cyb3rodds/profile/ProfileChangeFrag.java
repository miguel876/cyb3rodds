package enterprise.minura.cyb3rodds.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileChangeFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileChangeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileChangeFrag extends Fragment implements RestObserver{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Skyrunner mSky;
    Spinner sp;
    ArrayAdapter<CharSequence> adapterc;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileChangeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileChangeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileChangeFrag newInstance(String param1, String param2) {
        ProfileChangeFrag fragment = new ProfileChangeFrag();
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
        View v= lf.inflate(R.layout.fragment_profile_change, container, false);
        final User gb= (User) getActivity().getApplicationContext();

        mSky= new Skyrunner(20);
        mSky.addObserver(this);

        sp= v.findViewById(R.id.spincountry2);

        //Adapter Country--------------
        adapterc= ArrayAdapter.createFromResource(getActivity(), R.array.countries_array, R.layout.textspinner2);
        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapterc);

        final EditText first= v.findViewById(R.id.edfirst);
        final EditText last= v.findViewById(R.id.edlast);
        final EditText mail= v.findViewById(R.id.edemail);
        Button change= v.findViewById(R.id.change);
        ImageView info2= v.findViewById(R.id.btninfo2);
        ImageView back= v.findViewById(R.id.back);

        first.setText(gb.getFirstname().toString());
        last.setText(gb.getLastname().toString());
        mail.setText(gb.getEmail().toString());


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname= first.getText().toString();
                String lastname= last.getText().toString();
                String email= mail.getText().toString();
                String country= sp.getSelectedItem().toString();

                if(firstname.equals("") || firstname.equals(" ")){
                    Toast.makeText(getActivity(), "Please insert a valid First name!", Toast.LENGTH_SHORT).show();

                }
                else if(lastname.equals("") || lastname.equals(" ")){
                    Toast.makeText(getActivity(), "Please insert a valid Last name!", Toast.LENGTH_SHORT).show();

                }
                else if(email.equals("") || email.equals(" ")){
                    Toast.makeText(getActivity(), "Please insert a valid E-mail!", Toast.LENGTH_SHORT).show();

                }
                else if(!email.contains("@")){
                    Toast.makeText(getActivity(), "Please insert a valid E-mail!", Toast.LENGTH_SHORT).show();

                }
                else if(!email.contains(".")){
                    Toast.makeText(getActivity(), "Please insert a valid E-mail!", Toast.LENGTH_SHORT).show();

                }
                else if(firstname.equals(gb.getFirstname().toString()) && lastname.equals(gb.getLastname().toString()) && email.equals(gb.getEmail().toString()) && country.equals(gb.getCountry().toString())){
                    Toast.makeText(getActivity(), "Please update your profile with new data!", Toast.LENGTH_SHORT).show();

                }
                else{
                    gb.setFirstname(firstname);
                    gb.setLastname(lastname);
                    gb.setEmail(email);
                    gb.setCountry(country);

                    Toast.makeText(getActivity(), "Your profile has been sucessfully updated!", Toast.LENGTH_SHORT).show();

                    RequestR2D2 req= new RequestR2D2(Config.WSUPDATEPROFILE,null,RequestR2D2.GET);
                    req.addParValue("id",gb.getId());
                    req.addParValue("firstname",gb.getFirstname());
                    req.addParValue("lastname",gb.getLastname());
                    req.addParValue("email",gb.getEmail());
                    req.addParValue("country",gb.getCountry());

                    mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);

                    Fragment newFrag= new ProfileFrag();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();

                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();
                }

            }
        });
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("This is your profile, here you can change your account data as you desire!").setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setTitle("Profile").create();

                alert.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFrag= new ProfileFrag();
                FragmentTransaction ft= getFragmentManager().beginTransaction();

                ft.replace(R.id.framechange, newFrag);
                ft.commit();
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
