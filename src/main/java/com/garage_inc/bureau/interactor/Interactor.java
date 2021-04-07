package com.garage_inc.bureau.interactor;

public interface Interactor <In, Out> {

    Out run(In input) throws Exception;
}
