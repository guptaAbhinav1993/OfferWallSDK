# OfferWallSDK

Define below dependency into your gradle file

            dependencies {
	                    implementation ("com.github.guptaAbhinav1993:OfferWallSDK:1.0.7")
	        }   

Add this in build.gradle(:app)

	    dataBinding {
        	enabled = true
   	        }


Add this Jitpack maven url

            dependencyResolutionManagement {
		            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		            repositories {
			        mavenCentral()
			        maven { url 'https://jitpack.io' }
		        }
	        }


Give Internet permission in Manifest file

		<uses-permission android:name="android.permission.INTERNET" />


# IN KOTLIN :


Mention below code in Application class

            SDK().getInstance(this)

Mention below Code in to your Activity class

            val intent: Intent = OfferActivity.getIntentForOfferWall(this)
            startActivity(intent)


# IN JAVA :

Mention below code in Application class

		import com.brandmatic.offerwall.helper.SDK;

		new SDK().getInstance(this);


Mention below Code in to your Activity class

		import com.brandmatic.offerwall.activity.OfferActivity;

  
  		new OfferActivity();
                Intent intent = OfferActivity.Companion.getIntentForOfferWall(this);
                startActivity(intent);

