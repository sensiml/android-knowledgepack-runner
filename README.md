# Knowledge Pack Runner
This is a simple application to run a SensiML Knowledge Pack with test data using a JNI wrapper and a Knowledge Pack Library
built using the Android NDK.

## Adding a Knowledge Pack

To add your Knowledge Pack, copy the .so libraries (each supported ABI should have one in its own folder) into the folder
`app/knowledgepack-libs/src`

Copy the header/include files into `app/knowledgepack-libs/include`. It's ok to overwrite them for your application.


## Running a Knowledge Pack

The current JNI wrapper only runs test data. To run with sensor data, this can be taken and modified as needed to work with phone sensor data.

If testdata.h is empty (as is the checkin in this repository), the wrapper will always return -1.

In `knowledgepack-wrapper.cpp`, you will need to update the model names to run test data (as well as use yourn own knowledge packs). All calls to `KB_MODEL_parent_model_INDEX` should be replaced with the appropriate model found in `kb.h` of a download.


