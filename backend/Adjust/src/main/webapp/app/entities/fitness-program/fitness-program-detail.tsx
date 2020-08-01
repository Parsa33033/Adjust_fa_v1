import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fitness-program.reducer';
import { IFitnessProgram } from 'app/shared/model/fitness-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFitnessProgramDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FitnessProgramDetail = (props: IFitnessProgramDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { fitnessProgramEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.fitnessProgram.detail.title">FitnessProgram</Translate> [<b>{fitnessProgramEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="adjustApp.fitnessProgram.type">Type</Translate>
            </span>
          </dt>
          <dd>{fitnessProgramEntity.type}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.fitnessProgram.description">Description</Translate>
            </span>
          </dt>
          <dd>{fitnessProgramEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/fitness-program" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fitness-program/${fitnessProgramEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ fitnessProgram }: IRootState) => ({
  fitnessProgramEntity: fitnessProgram.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FitnessProgramDetail);
