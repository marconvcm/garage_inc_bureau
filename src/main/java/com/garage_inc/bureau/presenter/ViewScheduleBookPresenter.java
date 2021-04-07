package com.garage_inc.bureau.presenter;

import com.garage_inc.bureau.interactor.ViewScheduleBookInteractor;
import com.garage_inc.bureau.model.ScheduleBook;

public interface ViewScheduleBookPresenter {

    ScheduleBook present(ViewScheduleBookInteractor.Output output);
}
