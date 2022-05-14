package com.opackisebastian.carrental.domain.form.facade;

import java.util.List;

public interface CarFormFacade {

    List<String> retrieveCarIdsWithMatchingForm(List<String> formIds);

}
