import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './fitness-program.reducer';
import { IFitnessProgram } from 'app/shared/model/fitness-program.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFitnessProgramUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FitnessProgramUpdate = (props: IFitnessProgramUpdateProps) => {
  const [programId, setProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { fitnessProgramEntity, adjustPrograms, loading, updating } = props;

  const { description } = fitnessProgramEntity;

  const handleClose = () => {
    props.history.push('/fitness-program');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustPrograms();
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
        ...fitnessProgramEntity,
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
          <h2 id="adjustApp.fitnessProgram.home.createOrEditLabel">
            <Translate contentKey="adjustApp.fitnessProgram.home.createOrEditLabel">Create or edit a FitnessProgram</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fitnessProgramEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="fitness-program-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="fitness-program-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeLabel" for="fitness-program-type">
                  <Translate contentKey="adjustApp.fitnessProgram.type">Type</Translate>
                </Label>
                <AvField id="fitness-program-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="fitness-program-description">
                  <Translate contentKey="adjustApp.fitnessProgram.description">Description</Translate>
                </Label>
                <AvInput id="fitness-program-description" type="textarea" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/fitness-program" replace color="info">
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
  adjustPrograms: storeState.adjustProgram.entities,
  fitnessProgramEntity: storeState.fitnessProgram.entity,
  loading: storeState.fitnessProgram.loading,
  updating: storeState.fitnessProgram.updating,
  updateSuccess: storeState.fitnessProgram.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustPrograms,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FitnessProgramUpdate);
