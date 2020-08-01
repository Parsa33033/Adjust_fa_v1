import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IConversation } from 'app/shared/model/conversation.model';
import { getEntities as getConversations } from 'app/entities/conversation/conversation.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './adjust-client.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustClientUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustClientUpdate = (props: IAdjustClientUpdateProps) => {
  const [conversationId, setConversationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustClientEntity, conversations, loading, updating } = props;

  const { image, imageContentType } = adjustClientEntity;

  const handleClose = () => {
    props.history.push('/adjust-client');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getConversations();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustClientEntity,
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
          <h2 id="adjustApp.adjustClient.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustClient.home.createOrEditLabel">Create or edit a AdjustClient</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustClientEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-client-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-client-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="usernameLabel" for="adjust-client-username">
                  <Translate contentKey="adjustApp.adjustClient.username">Username</Translate>
                </Label>
                <AvField id="adjust-client-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="adjust-client-firstName">
                  <Translate contentKey="adjustApp.adjustClient.firstName">First Name</Translate>
                </Label>
                <AvField id="adjust-client-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="adjust-client-lastName">
                  <Translate contentKey="adjustApp.adjustClient.lastName">Last Name</Translate>
                </Label>
                <AvField id="adjust-client-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="birthDateLabel" for="adjust-client-birthDate">
                  <Translate contentKey="adjustApp.adjustClient.birthDate">Birth Date</Translate>
                </Label>
                <AvField id="adjust-client-birthDate" type="date" className="form-control" name="birthDate" />
              </AvGroup>
              <AvGroup>
                <Label id="ageLabel" for="adjust-client-age">
                  <Translate contentKey="adjustApp.adjustClient.age">Age</Translate>
                </Label>
                <AvField id="adjust-client-age" type="string" className="form-control" name="age" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="adjust-client-gender">
                  <Translate contentKey="adjustApp.adjustClient.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="adjust-client-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && adjustClientEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('adjustApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('adjustApp.Gender.FEMALE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="tokenLabel" for="adjust-client-token">
                  <Translate contentKey="adjustApp.adjustClient.token">Token</Translate>
                </Label>
                <AvField id="adjust-client-token" type="string" className="form-control" name="token" />
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="adjust-client-score">
                  <Translate contentKey="adjustApp.adjustClient.score">Score</Translate>
                </Label>
                <AvField id="adjust-client-score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageLabel" for="image">
                    <Translate contentKey="adjustApp.adjustClient.image">Image</Translate>
                  </Label>
                  <br />
                  {image ? (
                    <div>
                      {imageContentType ? (
                        <a onClick={openFile(imageContentType, image)}>
                          <img src={`data:${imageContentType};base64,${image}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {imageContentType}, {byteSize(image)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('image')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_image" type="file" onChange={onBlobChange(true, 'image')} accept="image/*" />
                  <AvInput type="hidden" name="image" value={image} />
                </AvGroup>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-client" replace color="info">
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
  conversations: storeState.conversation.entities,
  adjustClientEntity: storeState.adjustClient.entity,
  loading: storeState.adjustClient.loading,
  updating: storeState.adjustClient.updating,
  updateSuccess: storeState.adjustClient.updateSuccess,
});

const mapDispatchToProps = {
  getConversations,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustClientUpdate);
