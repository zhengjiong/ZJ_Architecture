/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.zj.example.arch.livedata.retrofit.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.zj.example.arch.livedata.retrofit.AppExecutors
import com.zj.example.arch.livedata.retrofit.api.ApiSuccessResponse
import com.zj.example.arch.livedata.retrofit.api.GithubService
import com.zj.example.arch.livedata.retrofit.vo.Resource
import com.zj.example.arch.livedata.retrofit.vo.User

/**
 * Repository that handles User objects.
 */
class UserRepository constructor(
        private val appExecutors: AppExecutors,
        private val githubService: GithubService
) {

    fun loadUser(): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                //userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb(): LiveData<User> {
                return MutableLiveData<User>()
            }

            override fun processResponse(response: ApiSuccessResponse<User>): User {
                return super.processResponse(response)
            }

            override fun createCall() = githubService.getUser("zhengjiong")
        }.asLiveData()
    }
}
