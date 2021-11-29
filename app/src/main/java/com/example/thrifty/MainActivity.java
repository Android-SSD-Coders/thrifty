package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;



import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amazonaws.mobileconnectors.pinpoint.targeting.TargetingClient;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfile;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfileUser;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.WomenClothesAdapter;
import com.example.thrifty.adapters.NewItemsAdapter;
import com.example.thrifty.adapters.PopularItemsAdapter;
import com.example.thrifty.adapters.SuggestedItemsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static PinpointManager pinpointManager;
    private static final String TAG = "MainActivity";

    private List<Product> NewProduct = new ArrayList<>();
    private List<Product> PopularProduct = new ArrayList<>();
    private List<Product> SuggestProduct = new ArrayList<>();
    private List<Product> categorizedProducts = new ArrayList<>();


    RecyclerView newItemRecView, suggestedRecView, popularRecView;
    NewItemsAdapter newItemsAdapter;
    SuggestedItemsAdapter suggestedItemsAdapter;
    PopularItemsAdapter popularItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configure();

        getPinpointManager(getApplicationContext());
        assignUserIdToEndpoint();
        createNotificationChannel();
        bottomNav();
        initRecyclerViews();


        Button admin = findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });

    }

    public void configure() {
        try {
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

    }

    private void initRecyclerViews() {
        newItemRecView = findViewById(R.id.newItemsRecView);
        suggestedRecView = findViewById(R.id.suggestedRecView);
        popularRecView = findViewById(R.id.popularRecView);

        newItemRecView.setAdapter(newItemsAdapter);
        newItemRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        suggestedRecView.setAdapter(suggestedItemsAdapter);
        suggestedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));

        popularRecView.setAdapter(popularItemsAdapter);
        popularRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));


        newItemRecView.setAdapter(new NewItemsAdapter(NewProduct, MainActivity.this));
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                newItemRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        popularRecView.setAdapter(new NewItemsAdapter(PopularProduct , MainActivity.this));
        Handler popularHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                popularRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        suggestedRecView.setAdapter(new NewItemsAdapter(SuggestProduct , MainActivity.this));
        Handler suggestHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                suggestedRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product product : response.getData()) {
                        NewProduct.add(product);
                    }
                    handler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product product : response.getData()) {
                        PopularProduct.add(product);
                    }
                    popularHandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product product : response.getData()) {
                        SuggestProduct.add(product);
                    }
                    suggestHandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

    private void bottomNav() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeNav);
        BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
        BottomNavigationItemView search = findViewById(R.id.search);
//        BottomNavigationItemView cart = findViewById(R.id.cart);
//        BottomNavigationItemView wishlist = findViewById(R.id.wishlist);
//        BottomNavigationItemView profile = findViewById(R.id.profile);

        search.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Categories.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        homeNav.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button stopButton = (Button) findViewById(R.id.admin);
        stopButton.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String email1 = sharedPreferences.getString("email", "Your email");
        if (email1.equals("aboud.coding@gmail.com")){
            stopButton.setVisibility(View.VISIBLE);
        }
    }

    public static PinpointManager getPinpointManager(Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });
            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d("TAG", "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(PushListenerService.CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void assignUserIdToEndpoint() {
        TargetingClient targetingClient = pinpointManager.getTargetingClient();
        EndpointProfile endpointProfile = targetingClient.currentEndpoint();
        EndpointProfileUser endpointProfileUser = new EndpointProfileUser();
        endpointProfileUser.setUserId("UserIdValue");
        endpointProfile.setUser(endpointProfileUser);
        targetingClient.updateEndpointProfile(endpointProfile);
        Log.d(TAG, "Assigned user ID " + endpointProfileUser.getUserId() +
                " to endpoint " + endpointProfile.getEndpointId());
    }

}