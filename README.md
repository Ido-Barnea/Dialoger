# :alarm_clock: Dialoger
A simple library to help developers create beautiful alert dialogs.

# :camera: Preview
<img src="https://github.com/Ido-Barnea/Dialoger/blob/master/images/loading_mockup.png" width="49%"/> <img src="https://github.com/Ido-Barnea/Dialoger/blob/master/images/success_mockup.png" width="49%"/>

# :question: How can I add this to my project?
> Step 1: Add the JitPack repository to your build file
  ```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
  ```
> Step 2: Add the dependency
  ```gradle
dependencies {
    implementation 'com.github.Ido-Barnea:Dialoger:1.1.6'
}
  ```
  That's it!

# Quick use :fast_forward:
  ```kotlin
val dialog = Dialoger(this, Dialoger.TYPE_LOADING)
    .setTitle("Loading...")
    .setDescription("This might take a while...")
    .setDrawable(R.drawable.loading)
    .setProgressBarColor(R.color.purple_200)
    .show()

// Dismiss the loading dialog after 5 seconds
Handler(Looper.getMainLooper()).postDelayed({
    dialog.dismiss();
  
    Dialoger(this, Dialoger.TYPE_MESSAGE)
      .setDialogColorTheme(R.color.purple_200)
      .setTitle("Beautiful Dialogue Title")
      .setDescription("An incredible dialogue message.")
      .setDrawable(R.drawable.my_cool_drawable)
      .setButtonText("Okay")
      .setButtonOnClickListener {
          Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
      }
      .show()
}, 5000);
  ```

# :book: Current Dialog Types:
- Message
- Loading
