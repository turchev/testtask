package com.github.turchev.carrepairshop.view.client;

import java.util.List;

import com.github.turchev.carrepairshop.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.MainLayout;
import com.github.turchev.carrepairshop.dao.ClientDao;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.domain.person.Client;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;


@Route(value = ClientView.NAME, layout = MainLayout.class)
@PageTitle(ClientView.NAME)
public class ClientView extends AbstractView {
    private static final Logger LOG = LogManager.getLogger();
    public static final String NAME = "client";
    private final ClientDao clientDao;
    private final Grid<Client> grid = new Grid<>();

    public ClientView() throws UiException {
        try {
            DaoFactory hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
            clientDao = hsqlDaoFactory.getClientDao();
            grid.setSelectionMode(SelectionMode.SINGLE);
            grid.addColumn(Client::getId).setHeader("Id").setId("id");
            grid.addColumn(Client::getLastName).setHeader("Фамилия").setId("lastName");
            grid.addColumn(Client::getFirstName).setHeader("Имя").setId("firstName");
            grid.addColumn(Client::getPatronnymic).setHeader("Отчество").setId("patronnymic");
            grid.addColumn(Client::getPhone).setHeader("Телефон").setId("phone");
            this.add(grid);
            showAll();
        } catch (Exception e) {
            throw new UiException(e);
        }
    }

    private void showAll(){
        try {
            List<Client> clients = clientDao.findAll();
            grid.setItems(clients);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private <T extends ClientDialogAbstract> void refreshGridAfterClosingDialog(T dialog) {
        dialog.addOpenedChangeListener(event -> {
            if (!event.isOpened()) {
                showAll();
            }
        });
    }

    @Override
    protected void btnAddClick() {
        try {
            ClientDialogAdd subWindowAdd = new ClientDialogAdd();
            subWindowAdd.open();
            refreshGridAfterClosingDialog(subWindowAdd);
        } catch (Exception e) {
            LOG.error(e);
            Notification.show("Ошибка диалогового окна создания записи");
        }
    }

    @Override
    protected void btnChangeClick() {
        try {
            if (grid.asSingleSelect().isEmpty()) {
                Notification.show("Выберите клиента из списка");
                return;
            }
            Client selectedClient = grid.asSingleSelect().getValue();
            ClientDialogEdit subWindowEdit = new ClientDialogEdit(selectedClient.getId());
            subWindowEdit.open();
            refreshGridAfterClosingDialog(subWindowEdit);
        } catch (Exception e) {
            LOG.error(e);
            Notification.show("Ошибка диалогового окна редактирования");
        }
    }

    @Override
    protected void btnDeleteClick() {
        try {
            if (grid.asSingleSelect().isEmpty()) {
                Notification.show("Выберите клиента из списка");
                return;
            }
            Client selectedClient = grid.asSingleSelect().getValue();
            final String MESSAGE_1 = "Удаление записи " + selectedClient.getLastName() + " "
                    + selectedClient.getFirstName() + " " + selectedClient.getPatronnymic() + "?";
            ConfirmDialog
                    .createWarning()
                    .withCaption("Внимание")
                    .withMessage(MESSAGE_1)
                    .withOkButton(() -> {
                        try {
                            clientDao.delete(selectedClient.getId());
                            showAll();
                        } catch (DaoException ex) {
                            LOG.debug(ex);
                            Notification.show(ex.getMessage());
                        } catch (Exception xe) {
                            LOG.error(xe);
                            Notification.show("Не удалось выполнить удаление");
                        }
                    }, ButtonOption.focus(), ButtonOption.caption("Подтвердить"))
                    .withCancelButton(ButtonOption.caption("Отменить"))
                    .open();
        } catch (Exception e) {
            LOG.error(e);
            Notification.show("Не удалось выполнить удаление");
        }
    }
}
