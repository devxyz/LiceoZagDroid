package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioAulaListAdapter;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.TreeSet;

public class OrarioAulaNonPersistenteFragment extends AbstractOrarioFragment<OrarioAulaListAdapter, RoomData> {

    @Override
    protected OrarioAulaListAdapter createAbstractOrarioListAdapter() {
        return new OrarioAulaListAdapter(getMainActivity(), filtro, containerOrari, giornoCorrente);
    }

    @Override
    protected RoomData[] getFilterValues() {
        final TreeSet<RoomData> aule = new TreeSet<>();
        for (RoomData x : ClassesAndRoomContainer.getAllValidRooms()) {
            if (!x.flagAulaFittizia())
                aule.add(x);
        }
        return aule.toArray(new RoomData[aule.size()]);
    }

    @Override
    protected void persistFiltrerValue(RoomData filtro) {
    }

    @Override
    protected RoomData retrievePersistedFiltrerValue() {
        return null;
    }

    @Override
    protected boolean isTimetableForTeacher() {
        return false;
    }

    @Override
    protected boolean isTimetableForStudents() {
        return false;
    }

    @Override
    protected boolean isTimetableForRooms() {
        return true;
    }

    @Override
    protected String filterToLabel(RoomData filter) {
        return filter == null ? "-" : filter.simpleName();
    }

    @Override
    protected boolean normalizeFilterName() {
        return false;
    }

    @Override
    protected void updateAdapter(RoomData filter) {
        super.orarioAdapter.setAula(filtro);
    }

    @Override
    protected String getFilterDialogLabel() {
        return "Aula";
    }

    @Override
    protected String getFilterAppLabel() {
        return "";
    }
}



