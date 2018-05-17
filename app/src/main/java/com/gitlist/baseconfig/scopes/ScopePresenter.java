package com.gitlist.baseconfig.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ScopePresenter {
}
