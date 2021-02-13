/*
 * Copyright 2018 Felipe Joglar Santos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.udacity.gradle.builditbigger;


import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class GetJokeAsyncTaskTest {

    @Test
    public void asyncTaskDoesNotReturnNullOrEmptyString() throws Exception {
        final MainActivityFragment mainActivityFragment = new MainActivityFragment();

        GetJokeAsyncTask getJokeAsyncTask = new GetJokeAsyncTask(new GetJokeAsyncTask.OnEventListener<String>() {
            @Override
            public void onSuccess(String joke) {
                mainActivityFragment.setJoke(joke);
            }
        });
        getJokeAsyncTask.execute();
        Thread.sleep(3000);

        assertThat(mainActivityFragment.getJoke(), not(isEmptyString()));
    }
}
