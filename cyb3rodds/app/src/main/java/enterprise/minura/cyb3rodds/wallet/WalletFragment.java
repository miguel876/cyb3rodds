package enterprise.minura.cyb3rodds.wallet;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import enterprise.minura.cyb3rodds.config.Config;
import enterprise.minura.cyb3rodds.model.User;
import enterprise.minura.cyb3rodds.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment implements RestObserver {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tvmoney,tvcyber;
    LinearLayout l10, l20, l30;
    int money, cyber;
    boolean charged;
    boolean chargedtrade;
    ImageView info;
    Spinner sptrade;
    Button trade;
    Skyrunner mSky;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
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
        View v= lf.inflate(R.layout.fragment_wallet, container, false);
        final User gb= (User) getActivity().getApplicationContext();

        mSky= new Skyrunner(20);
        mSky.addObserver(this);



        tvmoney= v.findViewById(R.id.money);
        tvcyber= v.findViewById(R.id.cybermoney);
        l10= v.findViewById(R.id.recharge10);
        l20= v.findViewById(R.id.recharge20);
        l30= v.findViewById(R.id.recharge30);
        info= v.findViewById(R.id.btninfo);
        trade= v.findViewById(R.id.trade);
        sptrade= v.findViewById(R.id.sptrade);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this.getActivity(), R.array.withdraw,android.R.layout.simple_spinner_item);
        sptrade.setAdapter(adapter);

        money=gb.getMoney();
        cyber=gb.getCyber();

        charged=gb.isCharged();
        chargedtrade= gb.isChargedtrade();

        tvmoney.setText(String.valueOf(money)+" €");
        tvcyber.setText(String.valueOf(cyber)+" C");


        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(money<10){
                    Toast.makeText(getActivity(),"You don't have enough money!", Toast.LENGTH_SHORT).show();

                }
               else if(charged==false){
                   gb.setMoney(money-10);
                   gb.setCyber(cyber+10000);

                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                   gb.setCharged(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();

                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();

                }
                else{
                   Toast.makeText(getActivity(),"This account has already been charged, please charge again tomorrow! Thank you!", Toast.LENGTH_SHORT).show();

               }
            }
        });
        l20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(money<20){
                    Toast.makeText(getActivity(),"You don't have enough money!", Toast.LENGTH_SHORT).show();

                }
                else if(charged==false){
                    gb.setMoney(money-20);
                    gb.setCyber(cyber+20000);

                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                    gb.setCharged(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();

                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();

                }
                else{
                    Toast.makeText(getActivity(),"This account has already been charged, please charge again tomorrow! Thank you!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        l30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(money<30){
                    Toast.makeText(getActivity(),"You don't have enough money!", Toast.LENGTH_SHORT).show();

                }
                else if(charged==false){
                    gb.setMoney(money-30);
                    gb.setCyber(cyber+30000);

                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                    gb.setCharged(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();

                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();

                }
                else{
                    Toast.makeText(getActivity(),"This account has already been charged, please charge again tomorrow! Thank you!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("This is your wallet, where you can control your money and where you can recharge money into your account, but only one time a day!").setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setTitle("Wallet").create();

                alert.show();
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String item= sptrade.getSelectedItem().toString();
                int value= Integer.parseInt(item);

                if(chargedtrade){
                    Toast.makeText(getActivity(),"You can only withdraw once a day!", Toast.LENGTH_SHORT).show();
                }
                else if(cyber<value){
                    Toast.makeText(getActivity(),"You don't have enough money to withdraw", Toast.LENGTH_SHORT).show();

                }

                else if(value==10000){
                    gb.setMoney(money+10);
                    gb.setCyber(cyber-10000);
                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                    gb.setChargedtrade(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();
                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();


                }
                else if(value==50000){
                    gb.setMoney(money+50);
                    gb.setCyber(cyber-50000);
                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                    gb.setChargedtrade(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();
                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();


                }
                else if(value==100000){
                    gb.setMoney(money+50);
                    gb.setCyber(cyber-50000);
                    tvmoney.setText(String.valueOf(money)+" €");
                    tvcyber.setText(String.valueOf(cyber)+" C");
                    gb.setChargedtrade(true);
                    Fragment newFrag= new WalletFragment();
                    FragmentTransaction ft= getFragmentManager().beginTransaction();
                    ft.replace(R.id.framechange, newFrag);
                    ft.commit();

                }
            }
        });

        RequestR2D2 req= new RequestR2D2(Config.WSUPDATEWALLET,null,RequestR2D2.GET);
        req.addParValue("id_utilizador",gb.getId());
        req.addParValue("cyb3rmoney",gb.getCyber());
        req.addParValue("realmoney",gb.getMoney());

        mSky.sendRequest(req, Skyrunner.RequestTag.KPOSONE);

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
        final User gb = (User) getActivity().getApplicationContext();


        if (response.getId() == Skyrunner.RequestTag.KPOSONE) {
            JSONObject jobj = null;

            try {

                JSONArray jar = response.getJSONArray();

                for (int i = 0; i < jar.length(); i++) {
                    jobj = jar.getJSONObject(i);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
