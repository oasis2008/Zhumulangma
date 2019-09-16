package com.gykj.zhumulangma.listen.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.gykj.zhumulangma.common.event.SingleLiveEvent;
import com.gykj.zhumulangma.common.mvvm.model.ZhumulangmaModel;
import com.gykj.zhumulangma.common.mvvm.viewmodel.BaseViewModel;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.model.column.Column;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Thomas.
 * Date: 2019/8/21 9:36
 * Email: 1071931588@qq.com
 * Description:
 */
public class DownloadViewModel extends BaseViewModel<ZhumulangmaModel> {
    private SingleLiveEvent<List<Column>> mColumnSingleLiveEvent;
    private int curPage = 1;
    public DownloadViewModel(@NonNull Application application, ZhumulangmaModel model) {
        super(application, model);
    }

    public void getRecommend(){
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CALC_DIMENSION, "0");
        map.put(DTransferConstants.PAGE, String.valueOf(curPage));
        mModel.getColumnList(map)
                .subscribe(columnList -> {
                    curPage++;
                    getColumnSingleLiveEvent().postValue(columnList.getColumns());
                }, e->e.printStackTrace());
    }

    public SingleLiveEvent<List<Column>> getColumnSingleLiveEvent() {
        return mColumnSingleLiveEvent=createLiveData(mColumnSingleLiveEvent);
    }
}
