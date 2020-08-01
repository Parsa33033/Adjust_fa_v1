import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { getEntities as getAdjustClients } from 'app/entities/adjust-client/adjust-client.reducer';
import { ISpecialist } from 'app/shared/model/specialist.model';
import { getEntities as getSpecialists } from 'app/entities/specialist/specialist.reducer';
import { getEntity, updateEntity, createEntity, reset } from './conversation.reducer';
import { IConversation } from 'app/shared/model/conversation.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IConversationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConversationUpdate = (props: IConversationUpdateProps) => {
  const [clientId, setClientId] = useState('0');
  const [specialistId, setSpecialistId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { conversationEntity, adjustClients, specialists, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/conversation');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustClients();
    props.getSpecialists();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...conversationEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.conversation.home.createOrEditLabel">
            <Translate contentKey="adjustApp.conversation.home.createOrEditLabel">Create or edit a Conversation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : conversationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="conversation-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="conversation-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label for="conversation-client">
                  <Translate contentKey="adjustApp.conversation.client">Client</Translate>
                </Label>
                <AvInput id="conversation-client" type="select" className="form-control" name="clientId">
                  <option value="" key="0" />
                  {adjustClients
                    ? adjustClients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="conversation-specialist">
                  <Translate contentKey="adjustApp.conversation.specialist">Specialist</Translate>
                </Label>
                <AvInput id="conversation-specialist" type="select" className="form-control" name="specialistId">
                  <option value="" key="0" />
                  {specialists
                    ? specialists.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/conversation" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adjustClients: storeState.adjustClient.entities,
  specialists: storeState.specialist.entities,
  conversationEntity: storeState.conversation.entity,
  loading: storeState.conversation.loading,
  updating: storeState.conversation.updating,
  updateSuccess: storeState.conversation.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustClients,
  getSpecialists,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConversationUpdate);
