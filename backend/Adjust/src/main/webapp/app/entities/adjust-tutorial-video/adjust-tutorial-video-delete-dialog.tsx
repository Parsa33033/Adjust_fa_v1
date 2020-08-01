import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IAdjustTutorialVideo } from 'app/shared/model/adjust-tutorial-video.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './adjust-tutorial-video.reducer';

export interface IAdjustTutorialVideoDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTutorialVideoDeleteDialog = (props: IAdjustTutorialVideoDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/adjust-tutorial-video');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.adjustTutorialVideoEntity.id);
  };

  const { adjustTutorialVideoEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="adjustApp.adjustTutorialVideo.delete.question">
        <Translate contentKey="adjustApp.adjustTutorialVideo.delete.question" interpolate={{ id: adjustTutorialVideoEntity.id }}>
          Are you sure you want to delete this AdjustTutorialVideo?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-adjustTutorialVideo" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ adjustTutorialVideo }: IRootState) => ({
  adjustTutorialVideoEntity: adjustTutorialVideo.entity,
  updateSuccess: adjustTutorialVideo.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTutorialVideoDeleteDialog);
