package com.example.grupoes.projetoes.presentation.presenters.api;


import com.example.grupoes.projetoes.beans.EditPointOfSaleBean;
import com.example.grupoes.projetoes.beans.EditProductBean;
import com.example.grupoes.projetoes.presentation.presenters.base.BasePresenter;
import com.example.grupoes.projetoes.presentation.ui.api.BaseView;
import com.example.grupoes.projetoes.util.InvalidInput;

import java.util.List;

public interface EditProductPresenter extends BasePresenter {

    interface View extends BaseView {
        void onSuccessfulEdit();
        void onInvalidInput(List<InvalidInput> invalidInputs);
    }

    void requestEditProduct(EditProductBean bean);
}
