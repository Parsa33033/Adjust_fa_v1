import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IExercise } from 'app/shared/model/exercise.model';
import { getEntities as getExercises } from 'app/entities/exercise/exercise.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './move.reducer';
import { IMove } from 'app/shared/model/move.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMoveUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MoveUpdate = (props: IMoveUpdateProps) => {
  const [exerciseId, setExerciseId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { moveEntity, exercises, loading, updating } = props;

  const { picture, pictureContentType } = moveEntity;

  const handleClose = () => {
    props.history.push('/move');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getExercises();
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
        ...moveEntity,
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
          <h2 id="adjustApp.move.home.createOrEditLabel">
            <Translate contentKey="adjustApp.move.home.createOrEditLabel">Create or edit a Move</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : moveEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="move-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="move-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="move-name">
                  <Translate contentKey="adjustApp.move.name">Name</Translate>
                </Label>
                <AvField id="move-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleNameLabel" for="move-muscleName">
                  <Translate contentKey="adjustApp.move.muscleName">Muscle Name</Translate>
                </Label>
                <AvField id="move-muscleName" type="text" name="muscleName" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleTypeLabel" for="move-muscleType">
                  <Translate contentKey="adjustApp.move.muscleType">Muscle Type</Translate>
                </Label>
                <AvInput
                  id="move-muscleType"
                  type="select"
                  className="form-control"
                  name="muscleType"
                  value={(!isNew && moveEntity.muscleType) || 'CHEST'}
                >
                  <option value="CHEST">{translate('adjustApp.MuscleType.CHEST')}</option>
                  <option value="SHOULDER">{translate('adjustApp.MuscleType.SHOULDER')}</option>
                  <option value="TRAPEZOID">{translate('adjustApp.MuscleType.TRAPEZOID')}</option>
                  <option value="BACK">{translate('adjustApp.MuscleType.BACK')}</option>
                  <option value="LATERAL">{translate('adjustApp.MuscleType.LATERAL')}</option>
                  <option value="BICEP">{translate('adjustApp.MuscleType.BICEP')}</option>
                  <option value="TRICEP">{translate('adjustApp.MuscleType.TRICEP')}</option>
                  <option value="FOREARM">{translate('adjustApp.MuscleType.FOREARM')}</option>
                  <option value="LEG">{translate('adjustApp.MuscleType.LEG')}</option>
                  <option value="HAMSTRING">{translate('adjustApp.MuscleType.HAMSTRING')}</option>
                  <option value="GLUTES">{translate('adjustApp.MuscleType.GLUTES')}</option>
                  <option value="CALVES">{translate('adjustApp.MuscleType.CALVES')}</option>
                  <option value="ABS">{translate('adjustApp.MuscleType.ABS')}</option>
                  <option value="MISC">{translate('adjustApp.MuscleType.MISC')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="equipmentLabel" for="move-equipment">
                  <Translate contentKey="adjustApp.move.equipment">Equipment</Translate>
                </Label>
                <AvField id="move-equipment" type="text" name="equipment" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="pictureLabel" for="picture">
                    <Translate contentKey="adjustApp.move.picture">Picture</Translate>
                  </Label>
                  <br />
                  {picture ? (
                    <div>
                      {pictureContentType ? (
                        <a onClick={openFile(pictureContentType, picture)}>
                          <img src={`data:${pictureContentType};base64,${picture}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {pictureContentType}, {byteSize(picture)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('picture')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_picture" type="file" onChange={onBlobChange(true, 'picture')} accept="image/*" />
                  <AvInput type="hidden" name="picture" value={picture} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label id="adjustMoveIdLabel" for="move-adjustMoveId">
                  <Translate contentKey="adjustApp.move.adjustMoveId">Adjust Move Id</Translate>
                </Label>
                <AvField id="move-adjustMoveId" type="string" className="form-control" name="adjustMoveId" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/move" replace color="info">
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
  exercises: storeState.exercise.entities,
  moveEntity: storeState.move.entity,
  loading: storeState.move.loading,
  updating: storeState.move.updating,
  updateSuccess: storeState.move.updateSuccess,
});

const mapDispatchToProps = {
  getExercises,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MoveUpdate);
