import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './adjust-move.reducer';
import { IAdjustMove } from 'app/shared/model/adjust-move.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustMoveUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustMoveUpdate = (props: IAdjustMoveUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustMoveEntity, loading, updating } = props;

  const { picture, pictureContentType } = adjustMoveEntity;

  const handleClose = () => {
    props.history.push('/adjust-move');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
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
        ...adjustMoveEntity,
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
          <h2 id="adjustApp.adjustMove.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustMove.home.createOrEditLabel">Create or edit a AdjustMove</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustMoveEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-move-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-move-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="adjust-move-name">
                  <Translate contentKey="adjustApp.adjustMove.name">Name</Translate>
                </Label>
                <AvField id="adjust-move-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleNameLabel" for="adjust-move-muscleName">
                  <Translate contentKey="adjustApp.adjustMove.muscleName">Muscle Name</Translate>
                </Label>
                <AvField id="adjust-move-muscleName" type="text" name="muscleName" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleTypeLabel" for="adjust-move-muscleType">
                  <Translate contentKey="adjustApp.adjustMove.muscleType">Muscle Type</Translate>
                </Label>
                <AvInput
                  id="adjust-move-muscleType"
                  type="select"
                  className="form-control"
                  name="muscleType"
                  value={(!isNew && adjustMoveEntity.muscleType) || 'CHEST'}
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
                <Label id="equipmentLabel" for="adjust-move-equipment">
                  <Translate contentKey="adjustApp.adjustMove.equipment">Equipment</Translate>
                </Label>
                <AvField id="adjust-move-equipment" type="text" name="equipment" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="pictureLabel" for="picture">
                    <Translate contentKey="adjustApp.adjustMove.picture">Picture</Translate>
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
              <Button tag={Link} id="cancel-save" to="/adjust-move" replace color="info">
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
  adjustMoveEntity: storeState.adjustMove.entity,
  loading: storeState.adjustMove.loading,
  updating: storeState.adjustMove.updating,
  updateSuccess: storeState.adjustMove.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustMoveUpdate);
