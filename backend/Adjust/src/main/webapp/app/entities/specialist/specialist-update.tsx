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
import { getEntity, updateEntity, createEntity, setBlob, reset } from './specialist.reducer';
import { ISpecialist } from 'app/shared/model/specialist.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISpecialistUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SpecialistUpdate = (props: ISpecialistUpdateProps) => {
  const [conversationId, setConversationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { specialistEntity, conversations, loading, updating } = props;

  const { image, imageContentType } = specialistEntity;

  const handleClose = () => {
    props.history.push('/specialist');
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
        ...specialistEntity,
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
          <h2 id="adjustApp.specialist.home.createOrEditLabel">
            <Translate contentKey="adjustApp.specialist.home.createOrEditLabel">Create or edit a Specialist</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : specialistEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="specialist-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="specialist-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="usernameLabel" for="specialist-username">
                  <Translate contentKey="adjustApp.specialist.username">Username</Translate>
                </Label>
                <AvField id="specialist-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="specialist-firstName">
                  <Translate contentKey="adjustApp.specialist.firstName">First Name</Translate>
                </Label>
                <AvField id="specialist-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="specialist-lastName">
                  <Translate contentKey="adjustApp.specialist.lastName">Last Name</Translate>
                </Label>
                <AvField id="specialist-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="birthLabel" for="specialist-birth">
                  <Translate contentKey="adjustApp.specialist.birth">Birth</Translate>
                </Label>
                <AvField id="specialist-birth" type="date" className="form-control" name="birth" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="specialist-gender">
                  <Translate contentKey="adjustApp.specialist.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="specialist-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && specialistEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('adjustApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('adjustApp.Gender.FEMALE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="degreeLabel" for="specialist-degree">
                  <Translate contentKey="adjustApp.specialist.degree">Degree</Translate>
                </Label>
                <AvField id="specialist-degree" type="text" name="degree" />
              </AvGroup>
              <AvGroup>
                <Label id="fieldLabel" for="specialist-field">
                  <Translate contentKey="adjustApp.specialist.field">Field</Translate>
                </Label>
                <AvField id="specialist-field" type="text" name="field" />
              </AvGroup>
              <AvGroup>
                <Label id="resumeLabel" for="specialist-resume">
                  <Translate contentKey="adjustApp.specialist.resume">Resume</Translate>
                </Label>
                <AvField id="specialist-resume" type="text" name="resume" />
              </AvGroup>
              <AvGroup>
                <Label id="starsLabel" for="specialist-stars">
                  <Translate contentKey="adjustApp.specialist.stars">Stars</Translate>
                </Label>
                <AvField id="specialist-stars" type="string" className="form-control" name="stars" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageLabel" for="image">
                    <Translate contentKey="adjustApp.specialist.image">Image</Translate>
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
              <AvGroup check>
                <Label id="busyLabel">
                  <AvInput id="specialist-busy" type="checkbox" className="form-check-input" name="busy" />
                  <Translate contentKey="adjustApp.specialist.busy">Busy</Translate>
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/specialist" replace color="info">
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
  specialistEntity: storeState.specialist.entity,
  loading: storeState.specialist.loading,
  updating: storeState.specialist.updating,
  updateSuccess: storeState.specialist.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(SpecialistUpdate);
