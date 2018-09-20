package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioClasseListAdapter;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;

import java.util.TreeSet;

public class OrarioClasseNonPersistenteFragment extends AbstractOrarioFragment<OrarioClasseListAdapter, ClassData> {

    @Override
    protected OrarioClasseListAdapter createAbstractOrarioListAdapter() {
        return new OrarioClasseListAdapter(getMainActivity(), (filtro), containerOrari, giornoCorrente);
    }

    @Override
    protected ClassData[] getFilterValues() {
        final TreeSet<ClassData> classi = new TreeSet<>();
        for (ClassData x : ClassesAndRoomContainer.getAllValidClasses()) {
            if (!x.flagClasseFittizia())
                classi.add(x);
        }
        return classi.toArray(new ClassData[classi.size()]);
    }

    @Override
    protected void persistFiltrerValue(ClassData filtro) {
    }

    @Override
    protected ClassData retrievePersistedFiltrerValue() {
        return null;
    }

    @Override
    protected boolean isTimetableForTeacher() {
        return false;
    }

    @Override
    protected boolean isTimetableForStudents() {
        return true;
    }

    @Override
    protected boolean isTimetableForRooms() {
        return false;
    }

    @Override
    protected String filterToLabel(ClassData filter) {
        return filter == null ? "-" : filter.className;
    }

    @Override
    protected boolean normalizeFilterName() {
        return false;
    }

    @Override
    protected void updateAdapter(ClassData filter) {
        super.orarioAdapter.setClasse(filter);
    }

    @Override
    protected String getFilterDialogLabel() {
        return "Classe";
    }

    @Override
    protected String getFilterAppLabel() {
        return "Classe";
    }
}



